package com.meme.designpattern.structural.criteria;

import java.util.List;

public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
