import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Sale } from './sale.model';
import { SalePopupService } from './sale-popup.service';
import { SaleService } from './sale.service';

@Component({
    selector: 'app-sale-delete-dialog',
    templateUrl: './sale-delete-dialog.component.html'
})
export class SaleDeleteDialogComponent {

    sale: Sale;

    constructor(
        private saleService: SaleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.saleService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'saleListModification',
                content: 'Deleted an sale'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'app-sale-delete-popup',
    template: ''
})
export class SaleDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private salePopupService: SalePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.salePopupService
                .open(SaleDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
