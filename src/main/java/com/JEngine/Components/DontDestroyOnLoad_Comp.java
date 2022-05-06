package com.JEngine.Components;

import com.JEngine.Core.Component;
import com.JEngine.Game.Visual.Scenes.GameScene;

public class DontDestroyOnLoad_Comp extends Component {

    public DontDestroyOnLoad_Comp() {
        super(true, "DontDestroyOnLoad");
    }
}
