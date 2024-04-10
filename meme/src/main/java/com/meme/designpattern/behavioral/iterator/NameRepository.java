package com.meme.designpattern.behavioral.iterator;

public class NameRepository implements Container {

    public String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new Iterator() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < names.length;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return names[index++];
                } else {
                    return null;
                }
            }
        };
    }
}
