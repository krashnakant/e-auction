import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Bid } from './bid.model';
import { BidPopupService } from './bid-popup.service';
import { BidService } from './bid.service';
import { Item, ItemService } from '../item';
import { User, UserService } from '../../shared';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'app-bid-dialog',
    templateUrl: './bid-dialog.component.html'
})
export class BidDialogComponent implements OnInit {

    bid: Bid;
    isSaving: boolean;

    items: Item[];

    users: User[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private bidService: BidService,
        private itemService: ItemService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.itemService.query()
            .subscribe((res: ResponseWrapper) => { this.items = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.bid.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bidService.update(this.bid));
        } else {
            this.subscribeToSaveResponse(
                this.bidService.create(this.bid));
        }
    }

    private subscribeToSaveResponse(result: Observable<Bid>) {
        result.subscribe((res: Bid) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Bid) {
        this.eventManager.broadcast({ name: 'bidListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackItemById(index: number, item: Item) {
        return item.id;
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'app-bid-popup',
    template: ''
})
export class BidPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bidPopupService: BidPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bidPopupService
                    .open(BidDialogComponent as Component, params['id']);
            } else {
                this.bidPopupService
                    .open(BidDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
