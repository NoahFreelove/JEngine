package com.basicexample;

import com.JEngine.PrimitiveTypes.Component;

public class CompTest extends Component {
    public CompTest() {
        super(true, "CompTest");
        setFields(new Object[]{"test", "test2", 3, 4.5f, true});
    }

    @Override
    public void Update() {
    }
}
