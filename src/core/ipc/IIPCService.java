package core.ipc;

import java.io.IOException;

import utilities.ILoggable;

public abstract class IIPCService implements ILoggable {

	protected int port;
	private boolean launchAtStartup;

	public IIPCService() {
		launchAtStartup = true;
	}

	public final void startRunning() throws IOException {
		if (!isRunning()) {
			start();
		} else {
			getLogger().info("This service is already running.");
		}
	}

	public final void stopRunning() throws IOException {
		if (!isRunning()) {
			return;
		}

		stop();
	}

	protected abstract void start() throws IOException;
	protected abstract void stop() throws IOException;

	public abstract boolean isRunning();

	public void setPort(int newPort) {
		if (isRunning()) {
			getLogger().warning("Cannot change port while running");
			return;
		}
		this.port = newPort;
	}

	public final int getPort() {
		return port;
	}

	public abstract String getName();

	public boolean isLaunchAtStartup() {
		return launchAtStartup;
	}

	public void setLaunchAtStartup(boolean launchAtStartup) {
		this.launchAtStartup = launchAtStartup;
	}
}
