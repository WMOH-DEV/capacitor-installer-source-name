export interface CapacitorInstallerSourceNamePlugin {
  /**
   * Gets information about how the app was installed.
   *
   * @returns Promise resolving to an object containing the installer source name
   * @since 1.0.0
   */
  getAppInstaller(): Promise<{ installer: string | null }>;
}
