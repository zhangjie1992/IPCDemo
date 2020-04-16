// ILeoAidl.aidl
package zhangjie1992.github.ipc;

// Declare any non-default types here with import statements

import zhangjie1992.github.Person;

interface ILeoAidl {

    void addPerson(in Person person);

    List<Person> getPersonList();

    int add(int a,int b);

}

