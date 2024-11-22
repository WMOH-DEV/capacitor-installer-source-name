export interface CapacitorInstallerSourceNamePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
