import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Sale } from './sale.model';
import { SaleService } from './sale.service';

@Component({
    selector: 'app-sale-detail',
    templateUrl: './sale-detail.component.html'
})
export class SaleDetailComponent implements OnInit, OnDestroy {

    sale: Sale;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private saleService: SaleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSales();
    }

    load(id) {
        this.saleService.find(id).subscribe((sale) => {
            this.sale = sale;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSales() {
        this.eventSubscriber = this.eventManager.subscribe(
            'saleListModification',
            (response) => this.load(this.sale.id)
        );
    }
}
