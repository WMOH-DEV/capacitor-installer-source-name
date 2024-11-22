import { WebPlugin } from '@capacitor/core';

import type { CapacitorInstallerSourceNamePlugin } from './definitions';

export class CapacitorInstallerSourceNameWeb extends WebPlugin implements CapacitorInstallerSourceNamePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
