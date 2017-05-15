package org.smart4j.framework.bean;

import java.lang.reflect.Method;

public class Handler {
	
	/**
	 * Controller ¿‡
	 */
	private Class<?> controllerClass;
	
	/**
	 * Action ∑Ω∑®
	 */
	private Method actionMethod;

	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;
	}
	
}
