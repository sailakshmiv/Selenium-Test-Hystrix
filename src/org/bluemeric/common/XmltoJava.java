package org.bluemeric.common;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bluemeric.common.GenericClass;

@XmlRootElement(name = "XmltoJava")
public class XmltoJava extends GenericClass {

	private Suite[] suite;
	private String name;

	@XmlElement
	public Suite[] getSuite() {
		return suite;
	}

	public void setSuite(Suite[] suite) {
		this.suite = suite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlRootElement
	public static class Suite {
		private Parameter[] parameter;

		private String suitename;
		private String classname;

		@XmlAttribute
		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}

		@XmlAttribute
		public String getSuitename() {
			return suitename;
		}

		public void setSuitename(String suitename) {
			this.suitename = suitename;
		}

		@XmlElement
		public Parameter[] getParameter() {
			return parameter;
		}

		public void setParameter(Parameter[] parameter) {
			this.parameter = parameter;
		}

		@Override
		public String toString() {
			return "Suite [parameter=" + Arrays.toString(parameter) + ", suitename=" + suitename + "]";
		}

	}

	@XmlRootElement
	public static class Parameter {

		private String name;
		private String testcase;
		private String dependson;
		private String param;
		private String param1;
		private String param2;
		private String param3;
		private String param4;
		private String param5;
		private String param6;
		private String param7;
		private String param8;
		private String param9;
		private String param10;
		
		@XmlAttribute
		public String getParam10() {
			return param10;
		}

		public void setParam10(String param10) {
			this.param10 = param10;
		}

		@XmlAttribute
		public String getParam4() {
			return param4;
		}

		public void setParam4(String param4) {
			this.param4 = param4;
		}
		@XmlAttribute
		public String getParam5() {
			return param5;
		}
		
		public void setParam5(String param5) {
			this.param5 = param5;
		}
		@XmlAttribute
		public String getParam6() {
			return param6;
		}

		public void setParam6(String param6) {
			this.param6 = param6;
		}
		@XmlAttribute
		public String getParam7() {
			return param7;
		}

		public void setParam7(String param7) {
			this.param7 = param7;
		}
		@XmlAttribute
		public String getParam8() {
			return param8;
		}

		public void setParam8(String param8) {
			this.param8 = param8;
		}
		@XmlAttribute
		public String getParam9() {
			return param9;
		}

		public void setParam9(String param9) {
			this.param9 = param9;
		}

		@XmlAttribute
		public String getDependson() {
			return dependson;
		}

		public void setDependson(String dependson) {
			this.dependson = dependson;
		}

		@XmlAttribute
		public String getParam3() {
			return param3;
		}

		public void setParam3(String param3) {
			this.param3 = param3;
		}

		@XmlAttribute
		public String getParam2() {
			return param2;
		}

		public void setParam2(String param2) {
			this.param2 = param2;
		}

		@XmlAttribute
		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		@XmlAttribute
		public String getParam1() {
			return param1;
		}

		public void setParam1(String param1) {
			this.param1 = param1;
		}

		@XmlAttribute
		public String getTestcase() {
			return testcase;
		}

		public void setTestcase(String testcase) {
			this.testcase = testcase;
		}

		@XmlAttribute
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Parameter [name=" + name + ", testcase=" + testcase + ", dependson=" + dependson + ", param="
					+ param + ", param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + ", param4=" + param4
					+ ", param5=" + param5 + ", param6=" + param6 + ", param7=" + param7 + ", param8=" + param8
					+ ", param9=" + param9 + ", param10=" + param10 + "]";
		}

		
		
		
		

	}

	@Override
	public String toString() {
		return "XmltoJava [suite=" + Arrays.toString(suite) + ", name=" + name + "]";
	}

}
