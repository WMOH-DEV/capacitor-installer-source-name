import { WebPlugin } from '@capacitor/core';

import type { CapacitorInstallerSourceNamePlugin } from './definitions';

export class CapacitorInstallerSourceNameWeb extends WebPlugin implements CapacitorInstallerSourceNamePlugin {
  async getAppInstaller(): Promise<{ installer: string | null }> {
    console.log('Web platform does not support getAppInstaller');
    return { installer: 'web' };
  }
}
