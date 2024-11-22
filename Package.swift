// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorInstallerSourceName",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorInstallerSourceName",
            targets: ["CapacitorInstallerSourceNamePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "CapacitorInstallerSourceNamePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/CapacitorInstallerSourceNamePlugin"),
        .testTarget(
            name: "CapacitorInstallerSourceNamePluginTests",
            dependencies: ["CapacitorInstallerSourceNamePlugin"],
            path: "ios/Tests/CapacitorInstallerSourceNamePluginTests")
    ]
)