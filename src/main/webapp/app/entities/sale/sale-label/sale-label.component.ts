import { Component, OnInit, Input } from '@angular/core';

import { Sale } from '../sale.model';
import { SaleService } from '../sale.service';

@Component({
    selector: 'app-sale-label',
    templateUrl: './sale-label.component.html'
})
export class SaleLabelComponent implements OnInit {
    @Input()
    id: number;
    sale: Sale;

    constructor(
        private saleService: SaleService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.saleService.find(id).subscribe((sale) => {
            this.sale = sale;
        });
    }
    previousState() {
        window.history.back();
    }
}
