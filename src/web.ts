import { WebPlugin } from '@capacitor/core';

import type { CapacitorInstallerSourceNamePlugin } from './definitions';

export class CapacitorInstallerSourceNameWeb extends WebPlugin implements CapacitorInstallerSourceNamePlugin {
  async getAppInstaller(): Promise<{ installer: string | null }> {
    // On web, we can detect some information about how the app was accessed
    const userAgent = navigator.userAgent;
    const isStandalone = window.matchMedia('(display-mode: standalone)').matches;
    const isPWA = window.navigator && 'serviceWorker' in window.navigator;

    if (isStandalone) {
      return { installer: 'PWA (Installed)' };
    } else if (isPWA) {
      return { installer: 'PWA (Browser)' };
    } else if (userAgent.includes('Electron')) {
      return { installer: 'Electron' };
    } else {
      return { installer: 'Web Browser' };
    }
  }
}
