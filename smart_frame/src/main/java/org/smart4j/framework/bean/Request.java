package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Request {
	
	/**
	 * ���󷽷�
	 */
	private String requestMethod;
	
	/**
	 * ����·��
	 */
	private String requestPath;
	
	public Request(String requestMethod, String requestPath){
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((requestMethod == null) ? 0 : requestMethod.hashCode());
//		result = prime * result + ((requestPath == null) ? 0 : requestPath.hashCode());
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Request other = (Request) obj;
//		if (requestMethod == null) {
//			if (other.requestMethod != null)
//				return false;
//		} else if (!requestMethod.equals(other.requestMethod))
//			return false;
//		if (requestPath == null) {
//			if (other.requestPath != null)
//				return false;
//		} else if (!requestPath.equals(other.requestPath))
//			return false;
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
