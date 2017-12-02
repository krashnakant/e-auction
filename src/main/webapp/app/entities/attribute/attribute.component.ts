import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Attribute } from './attribute.model';
import { AttributeService } from './attribute.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'app-attribute',
    templateUrl: './attribute.component.html'
})
export class AttributeComponent implements OnInit, OnDestroy {
attributes: Attribute[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private attributeService: AttributeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.attributeService.query().subscribe(
            (res: ResponseWrapper) => {
                this.attributes = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAttributes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Attribute) {
        return item.id;
    }
    registerChangeInAttributes() {
        this.eventSubscriber = this.eventManager.subscribe('attributeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
