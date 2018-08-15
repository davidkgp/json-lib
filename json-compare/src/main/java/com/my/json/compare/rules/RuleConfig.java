package com.my.json.compare.rules;

import java.util.HashMap;

public class RuleConfig {
	
	private static HashMap<String,Class> ruleConfigs = new HashMap<String, Class>()
	{{
	     put("type", TypeRule.class);
	     put("value", ValueRule.class);
	     put("default", ValueRule.class);
	}};
	
	public static Class getRule(String type) {
		return type!=null && ruleConfigs.get(type)!=null?ruleConfigs.get(type):getDefaultRule();
	}
	
	public static Class getDefaultRule() {
		return ruleConfigs.get("default");
	}
	
	public static void setRule(String key,Class rule) {
		ruleConfigs.put(key, rule);
	}

}
