import { CapacitorInstallerSourceName } from 'capacitor-installer-source-name';

window.getInstallerSource = async () => {
    try {
        const result = await CapacitorInstallerSourceName.getAppInstaller();
        document.getElementById("result").innerHTML = `
            <h3>App Installer Information:</h3>
            <p><strong>Source:</strong> ${result.installer}</p>
        `;
    } catch (error) {
        document.getElementById("result").innerHTML = `
            <h3>Error:</h3>
            <p style="color: red;">${error.message}</p>
        `;
    }
}
