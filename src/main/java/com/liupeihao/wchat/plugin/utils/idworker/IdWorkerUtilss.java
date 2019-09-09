package com.liupeihao.wchat.plugin.utils.idworker;

public class IdWorkerUtilss {

	private IdWorkerUtilss() {

	}

	private static final IdWorker idWorker = new IdWorker(1, 1);

	private static IdWorker getInstance() {
		return idWorker;
	}

	public static Long getId() {
		return getInstance().nextId();
	}

	public static String getIdStr() {
		return getInstance().nextId() + "";
	}

}
