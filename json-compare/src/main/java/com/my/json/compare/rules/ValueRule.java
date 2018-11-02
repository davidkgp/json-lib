package com.my.json.compare.rules;
import com.my.json.compare.rules.interfaces.ComparisionRule;

public class ValueRule implements ComparisionRule {

	@Override
	public boolean compareData(Object superSet, Object subSet) {
		
		return new TypeRule().compareData(superSet, subSet)?superSet.equals(subSet):false;
	}

}
