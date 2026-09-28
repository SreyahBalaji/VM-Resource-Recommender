async function recommendVM() {

    const numberOfUsers =
        document.getElementById("numberOfUsers").value;

    const storageRequired =
        document.getElementById("storageRequired").value;

    if (!numberOfUsers || !storageRequired) {
        alert("Please enter all required values.");
        return;
    }

    if (numberOfUsers <= 0 || storageRequired <= 0) {
        alert("Users and storage must be greater than 0.");
        return;
    }

    const request = {

        workloadType:
        document.getElementById("workloadType").value,

        numberOfUsers:
            parseInt(numberOfUsers),

        workloadIntensity:
        document.getElementById("workloadIntensity").value,

        databaseRequired:
            document.getElementById("databaseRequired").value === "true",

        storageRequired:
            parseInt(storageRequired)
    };

    try {

        const response = await fetch("/api/recommend", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(request)
        });

        if (!response.ok) {
            throw new Error("Recommendation request failed.");
        }

        const data = await response.json();

        document.getElementById("cpu").innerText =
            data.cpu;

        document.getElementById("ram").innerText =
            data.ram;

        document.getElementById("storage").innerText =
            data.storage;

        document.getElementById("category").innerText =
            data.vmCategory;

        document.getElementById("reason").innerText =
            data.reason;
        document.getElementById("azureVm").innerText =
            data.azureVmName;

        document.getElementById("azureConfig").innerText =
            data.azureCpu + " vCPU / " +
            data.azureRam + " GB RAM";

        document.getElementById("azureRegion").innerText =
            data.azureRegion;

        if (data.hourlyPrice >= 0) {

            document.getElementById("hourlyPrice").innerText =
                "$" + data.hourlyPrice + " / hour";

            document.getElementById("monthlyPrice").innerText =
                "$" + data.monthlyPrice + " / month";

        } else {

            document.getElementById("hourlyPrice").innerText =
                "Price unavailable";

            document.getElementById("monthlyPrice").innerText =
                "Price unavailable";
        }

        document
            .getElementById("result")
            .classList.remove("hidden");

    } catch (error) {

        console.error(error);

        alert(
            "Could not get the recommendation. " +
            "Please make sure the server is running."
        );
    }
}