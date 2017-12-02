import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ItemAttribute } from './item-attribute.model';
import { ItemAttributeService } from './item-attribute.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'app-item-attribute',
    templateUrl: './item-attribute.component.html'
})
export class ItemAttributeComponent implements OnInit, OnDestroy {
itemAttributes: ItemAttribute[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemAttributeService: ItemAttributeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.itemAttributeService.query().subscribe(
            (res: ResponseWrapper) => {
                this.itemAttributes = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInItemAttributes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ItemAttribute) {
        return item.id;
    }
    registerChangeInItemAttributes() {
        this.eventSubscriber = this.eventManager.subscribe('itemAttributeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
