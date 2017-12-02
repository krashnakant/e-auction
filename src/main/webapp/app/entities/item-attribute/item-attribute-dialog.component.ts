import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemAttribute } from './item-attribute.model';
import { ItemAttributePopupService } from './item-attribute-popup.service';
import { ItemAttributeService } from './item-attribute.service';
import { Attribute, AttributeService } from '../attribute';
import { Item, ItemService } from '../item';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'app-item-attribute-dialog',
    templateUrl: './item-attribute-dialog.component.html'
})
export class ItemAttributeDialogComponent implements OnInit {

    itemAttribute: ItemAttribute;
    isSaving: boolean;

    attributes: Attribute[];

    items: Item[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private itemAttributeService: ItemAttributeService,
        private attributeService: AttributeService,
        private itemService: ItemService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.attributeService.query()
            .subscribe((res: ResponseWrapper) => { this.attributes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.itemService.query()
            .subscribe((res: ResponseWrapper) => { this.items = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.itemAttribute.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itemAttributeService.update(this.itemAttribute));
        } else {
            this.subscribeToSaveResponse(
                this.itemAttributeService.create(this.itemAttribute));
        }
    }

    private subscribeToSaveResponse(result: Observable<ItemAttribute>) {
        result.subscribe((res: ItemAttribute) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ItemAttribute) {
        this.eventManager.broadcast({ name: 'itemAttributeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAttributeById(index: number, item: Attribute) {
        return item.id;
    }

    trackItemById(index: number, item: Item) {
        return item.id;
    }
}

@Component({
    selector: 'app-item-attribute-popup',
    template: ''
})
export class ItemAttributePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemAttributePopupService: ItemAttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itemAttributePopupService
                    .open(ItemAttributeDialogComponent as Component, params['id']);
            } else {
                this.itemAttributePopupService
                    .open(ItemAttributeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
