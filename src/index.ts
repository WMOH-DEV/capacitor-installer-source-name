import { registerPlugin } from '@capacitor/core';

import type { CapacitorInstallerSourceNamePlugin } from './definitions';

const CapacitorInstallerSourceName = registerPlugin<CapacitorInstallerSourceNamePlugin>(
  'CapacitorInstallerSourceName',
  {
    web: () => import('./web').then((m) => new m.CapacitorInstallerSourceNameWeb()),
  },
);

export * from './definitions';
export { CapacitorInstallerSourceName };
