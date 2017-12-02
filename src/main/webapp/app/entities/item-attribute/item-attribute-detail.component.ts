import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ItemAttribute } from './item-attribute.model';
import { ItemAttributeService } from './item-attribute.service';

@Component({
    selector: 'app-item-attribute-detail',
    templateUrl: './item-attribute-detail.component.html'
})
export class ItemAttributeDetailComponent implements OnInit, OnDestroy {

    itemAttribute: ItemAttribute;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private itemAttributeService: ItemAttributeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItemAttributes();
    }

    load(id) {
        this.itemAttributeService.find(id).subscribe((itemAttribute) => {
            this.itemAttribute = itemAttribute;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItemAttributes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itemAttributeListModification',
            (response) => this.load(this.itemAttribute.id)
        );
    }
}
