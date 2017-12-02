import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItemAttribute } from './item-attribute.model';
import { ItemAttributePopupService } from './item-attribute-popup.service';
import { ItemAttributeService } from './item-attribute.service';

@Component({
    selector: 'app-item-attribute-delete-dialog',
    templateUrl: './item-attribute-delete-dialog.component.html'
})
export class ItemAttributeDeleteDialogComponent {

    itemAttribute: ItemAttribute;

    constructor(
        private itemAttributeService: ItemAttributeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemAttributeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itemAttributeListModification',
                content: 'Deleted an itemAttribute'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'app-item-attribute-delete-popup',
    template: ''
})
export class ItemAttributeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemAttributePopupService: ItemAttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itemAttributePopupService
                .open(ItemAttributeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
