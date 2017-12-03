import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Bid } from '../bid.model';
import { BidService } from '../bid.service';
import { Principal } from '../../../shared';

@Component({
    selector: 'app-bid-now',
    templateUrl: 'bid-now.component.html'
})

export class BidNowComponent implements OnInit {
    @Input()
    item: number;

    amount: number;
    isSaving: boolean;

    constructor(
        private bidService: BidService,
        private principal: Principal,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) { }

    ngOnInit() { }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    save() {
        this.isSaving = true;
        this.subscribeToSaveResponse(
            this.bidService.bid(JSON.stringify({ item: this.item, amount: this.amount })));
    }

    private subscribeToSaveResponse(result: Observable<Bid>) {
        result.subscribe((res: Bid) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Bid) {
        this.eventManager.broadcast({ name: 'bidListModification', content: 'OK' });
        this.isSaving = false;
        alert('Success');
    }

    private onSaveError() {
        alert('Error');
        this.isSaving = false;
    }
}
