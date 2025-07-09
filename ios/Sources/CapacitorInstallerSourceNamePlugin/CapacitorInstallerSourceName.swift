import Foundation

@objc public class CapacitorInstallerSourceName: NSObject {
    @objc public func getAppInstaller() -> String {
        // On iOS, apps can only be installed from:
        // 1. App Store (normal case)
        // 2. TestFlight (beta testing)
        // 3. Enterprise/Developer installation (rare)
        // 4. Sideloading via Xcode/developer tools (development)
        
        // Check if this is a TestFlight build
        if isTestFlightBuild() {
            return "TestFlight"
        }
        
        // Check if this is a development build
        if isDevelopmentBuild() {
            return "Development/Xcode"
        }
        
        // Check if this might be an enterprise build
        if isEnterpriseBuild() {
            return "Enterprise"
        }
        
        // Default assumption for App Store
        return "App Store"
    }
    
    private func isTestFlightBuild() -> Bool {
        // TestFlight builds have a specific receipt structure
        guard let appStoreReceiptURL = Bundle.main.appStoreReceiptURL else {
            return false
        }
        
        let receiptURLString = appStoreReceiptURL.absoluteString
        return receiptURLString.contains("sandboxReceipt")
    }
    
    private func isDevelopmentBuild() -> Bool {
        // Development builds usually have embedded provisioning profiles
        guard let provisioningPath = Bundle.main.path(forResource: "embedded", ofType: "mobileprovision") else {
            return false
        }
        
        // If we can read the provisioning profile, check if it's a development profile
        do {
            let provisioningData = try Data(contentsOf: URL(fileURLWithPath: provisioningPath))
            let provisioningString = String(data: provisioningData, encoding: .ascii) ?? ""
            
            // Development profiles typically contain "development" or specific development identifiers
            return provisioningString.contains("development") || 
                   provisioningString.contains("Xcode") ||
                   provisioningString.contains("get-task-allow")
        } catch {
            return false
        }
    }
    
    private func isEnterpriseBuild() -> Bool {
        // Enterprise builds have specific characteristics
        // This is a basic check - enterprise detection can be complex
        guard let provisioningPath = Bundle.main.path(forResource: "embedded", ofType: "mobileprovision") else {
            return false
        }
        
        do {
            let provisioningData = try Data(contentsOf: URL(fileURLWithPath: provisioningPath))
            let provisioningString = String(data: provisioningData, encoding: .ascii) ?? ""
            
            return provisioningString.contains("enterprise") || 
                   provisioningString.contains("ProvisionsAllDevices")
        } catch {
            return false
        }
    }
}
