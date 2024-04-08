package br.com.sigvet.api.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseCrudController<DomainObject, CreateRequest, UpdateRequest, ResponseObject> 
    implements ICrudOperations<CreateRequest, UpdateRequest, ResponseObject> {
            
    @Autowired
    protected DomainObjectUseCaseManager<DomainObject> domainObjectUseCaseManager;

}
