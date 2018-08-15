package com.my.json.compare.rules;

import com.my.json.compare.rules.interfaces.ComparisionRule;

public class TypeRule implements ComparisionRule {

	@Override
	public boolean compareData(Object superSet, Object subSet) {
		// TODO Auto-generated method stub
		return superSet.getClass().equals(subSet.getClass());
	}

}
