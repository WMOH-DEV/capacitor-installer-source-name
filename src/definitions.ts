export interface CapacitorInstallerSourceNamePlugin {
  getAppInstaller(): Promise<{ installer: string | null }>;
}
