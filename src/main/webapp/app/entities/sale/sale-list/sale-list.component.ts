import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Sale } from '../sale.model';
import { SaleService } from '../sale.service';
import { Principal, ResponseWrapper } from '../../../shared';

@Component({
    selector: 'app-sale-list',
    templateUrl: './sale-list.component.html'
})
export class SaleListComponent implements OnInit {

currentAccount: any;
    sales: Sale[];
    category: String;
    eventSubscriber: Subscription;

    constructor(
        private saleService: SaleService,
        private jhiAlertService: JhiAlertService,
        private activatedRoute: ActivatedRoute
    ) { }

    load(id) {
        this.saleService.list(id).subscribe((sales) => {
            this.sales = sales;
        }, (error) => this.onError(error));
    }

    ngOnInit() {
        this.eventSubscriber = this.activatedRoute.params.subscribe((params) => {
            this.load(params['id']);
            this.category = params['cat'];
        });
    }

    trackId(index: number, item: Sale) {
        return item.id;
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
