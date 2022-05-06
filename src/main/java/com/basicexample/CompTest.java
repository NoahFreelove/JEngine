package com.basicexample;

import com.JEngine.Core.Component;

public class CompTest extends Component {
    public CompTest() {
        super(true, "CompTest");
        setFields(new Object[]{"test", "test2", 3, 4.5f, true});
    }

    @Override
    public void Update() {
    }
}
