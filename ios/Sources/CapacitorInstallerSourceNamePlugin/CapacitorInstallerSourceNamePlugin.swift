import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorInstallerSourceNamePlugin)
public class CapacitorInstallerSourceNamePlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "CapacitorInstallerSourceNamePlugin"
    public let jsName = "CapacitorInstallerSourceName"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = CapacitorInstallerSourceName()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
