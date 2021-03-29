//
//  DependencyContainer.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-29.
//  Copyright © 2021 orgName. All rights reserved.
//

/**
 Idea from:
 https://www.raywenderlich.com/14223279-dependency-injection-tutorial-for-ios-getting-started
 */

import Foundation

protocol DiContainerProtocol{
    func register<Component>(type: Component.Type, component: Any)
    func resolve<Component>(type: Component.Type) -> Component?
    
    func register(name: String, component: Any)
    func resolve<Component>(name: String) -> Component?
}

final class DIContainer: DiContainerProtocol{
    
    static let shared = DIContainer()
    
    private init(){}
    
    var components: [String: Any] = [:]
    
    func register<Component>(type: Component.Type, component: Any){
        components["\(type)"] = component
    }
    
    func register(name: String, component: Any){
        components[name] = component
    }
    
    func resolve<Component>(type: Component.Type) -> Component? {
        return components["\(type)"] as? Component
    }
    
    func resolve<Component>(name: String) -> Component? {
        return components[name] as? Component
    }
}


