# capacitor-installer-source-name

A Capacitor plugin that detects the installation source of your app across different platforms.

## Features

- 🤖 **Android**: Detects Google Play Store, APK sideloading, third-party app stores, and more
- 🍎 **iOS**: Identifies App Store, TestFlight, Enterprise, or Development installations
- 🌐 **Web**: Determines PWA installation status or browser access
- 📦 **40+ Android App Stores**: Comprehensive mapping of popular app stores and installation sources

## Install

```bash
npm install capacitor-installer-source-name
npx cap sync
```

## Usage

```typescript
import { CapacitorInstallerSourceName } from 'capacitor-installer-source-name';

const getInstaller = async () => {
  try {
    const result = await CapacitorInstallerSourceName.getAppInstaller();
    console.log('App installed from:', result.installer);
  } catch (error) {
    console.error('Error getting installer info:', error);
  }
};
```

## Platform-Specific Return Values

### Android

- `"Google Play Store"` - Installed from Google Play
- `"Amazon App Store"` - Installed from Amazon Appstore
- `"Samsung Galaxy Store"` - Installed from Samsung's store
- `"Huawei AppGallery"` - Installed from Huawei's store
- `"F-Droid"` - Installed from F-Droid open source store
- `"APKPure"`, `"Uptodown"`, `"Aptoide"` - Third-party stores
- `"Unknown source"` - Sideloaded APK or unknown installer
- Package name (e.g., `"com.example.installer"`) - Unrecognized installer

### iOS

- `"App Store"` - Standard App Store installation
- `"TestFlight"` - Beta testing via TestFlight
- `"Development/Xcode"` - Development build via Xcode
- `"Enterprise"` - Enterprise distribution

### Web

- `"PWA (Installed)"` - Installed as Progressive Web App
- `"PWA (Browser)"` - PWA-capable but running in browser
- `"Electron"` - Running in Electron wrapper
- `"Web Browser"` - Standard web browser access

## API

<docgen-index>

* [`getAppInstaller()`](#getappinstaller)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getAppInstaller()

```typescript
getAppInstaller() => Promise<{ installer: string | null; }>
```

Gets information about how the app was installed.

**Returns:** <code>Promise&lt;{ installer: string | null; }&gt;</code>

**Since:** 1.0.0

--------------------

</docgen-api>

## Use Cases

- **Analytics**: Track installation sources for app distribution insights
- **Security**: Identify potentially unsafe installation methods
- **Feature Gating**: Enable/disable features based on installation source
- **Support**: Provide different support channels based on install source
- **Marketing Attribution**: Understand which distribution channels are most effective

## License

MIT
