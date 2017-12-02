import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Sale } from './sale.model';
import { SalePopupService } from './sale-popup.service';
import { SaleService } from './sale.service';
import { Category, CategoryService } from '../category';
import { UserAccount, UserAccountService } from '../user-account';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'app-sale-dialog',
    templateUrl: './sale-dialog.component.html'
})
export class SaleDialogComponent implements OnInit {

    sale: Sale;
    isSaving: boolean;

    categories: Category[];

    useraccounts: UserAccount[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private saleService: SaleService,
        private categoryService: CategoryService,
        private userAccountService: UserAccountService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.categoryService.query()
            .subscribe((res: ResponseWrapper) => { this.categories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.userAccountService.query()
            .subscribe((res: ResponseWrapper) => { this.useraccounts = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sale.id !== undefined) {
            this.subscribeToSaveResponse(
                this.saleService.update(this.sale));
        } else {
            this.subscribeToSaveResponse(
                this.saleService.create(this.sale));
        }
    }

    private subscribeToSaveResponse(result: Observable<Sale>) {
        result.subscribe((res: Sale) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Sale) {
        this.eventManager.broadcast({ name: 'saleListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCategoryById(index: number, item: Category) {
        return item.id;
    }

    trackUserAccountById(index: number, item: UserAccount) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'app-sale-popup',
    template: ''
})
export class SalePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private salePopupService: SalePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.salePopupService
                    .open(SaleDialogComponent as Component, params['id']);
            } else {
                this.salePopupService
                    .open(SaleDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
