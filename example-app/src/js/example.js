import { CapacitorInstallerSourceName } from 'capacitor-installer-source-name';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    CapacitorInstallerSourceName.echo({ value: inputValue })
}
