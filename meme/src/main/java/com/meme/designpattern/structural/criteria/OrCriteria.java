package com.meme.designpattern.structural.criteria;

import java.util.List;

public class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> result = criteria.meetCriteria(persons);
        List<Person> otherResult = otherCriteria.meetCriteria(persons);
        for (Person person : otherResult) {
            if (!result.contains(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
