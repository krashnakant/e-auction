import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionItemModule } from '../item/item.module';

import {
    SaleService,
    SalePopupService,
    SaleComponent,
    SaleDetailComponent,
    SaleDialogComponent,
    SalePopupComponent,
    SaleDeletePopupComponent,
    SaleDeleteDialogComponent,
    SaleListComponent,
    saleRoute,
    salePopupRoute,
    SaleResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...saleRoute,
    ...salePopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionItemModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SaleComponent,
        SaleDetailComponent,
        SaleDialogComponent,
        SaleDeleteDialogComponent,
        SalePopupComponent,
        SaleDeletePopupComponent,
        SaleListComponent
    ],
    entryComponents: [
        SaleComponent,
        SaleDialogComponent,
        SalePopupComponent,
        SaleDeleteDialogComponent,
        SaleDeletePopupComponent,
        SaleListComponent
    ],
    providers: [
        SaleService,
        SalePopupService,
        SaleResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        SaleListComponent
    ]
})
export class EauctionSaleModule {}
