package net.coca.baseclient.mod;


import net.coca.baseclient.mod.impl.render.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mods {

    private List<Module> modules = new ArrayList<>();

    public static final Mods INSTANCE = new Mods();

    public Mods() {
    }
    public void init(){
        add(new Arraylist());
        add(new place2());
        add(new placeholder1());
        add(new place3());

    }

    public void add(Module m) {
        modules.add(m);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream()
                .filter(module -> module.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public Module getModuleByClass(Class<? extends Module> cls) {
        return modules.stream()
                .filter(m -> m.getClass().equals(cls))
                .findFirst()
                .orElse(null);
    }

    public List<Module> getEnabledModules() {
        return modules.stream()
                .filter(Module::isEnabled)
                .collect(Collectors.toList());
    }

    public List<RenderModule> getEnabledRenderModules() {
        return modules.stream()
                .filter(Module::isEnabled)
                .filter(module -> module instanceof RenderModule)
                .map(module -> (RenderModule) module)
                .collect(Collectors.toList());
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}