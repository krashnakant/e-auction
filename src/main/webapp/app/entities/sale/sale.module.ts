import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionItemModule } from '../item/item.module';

import {
    SaleService,
    SalePopupService,
    SaleComponent,
    SaleListComponent,
    SaleLabelComponent,
    SaleDetailComponent,
    SaleDialogComponent,
    SalePopupComponent,
    SaleDeletePopupComponent,
    SaleDeleteDialogComponent,
    saleRoute,
    salePopupRoute,
    SaleResolvePagingParams,
} from './';
import { EauctionCategoryModule } from '../category/category.module';

const ENTITY_STATES = [
    ...saleRoute,
    ...salePopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionItemModule,
        EauctionCategoryModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SaleComponent,
        SaleListComponent,
        SaleLabelComponent,
        SaleDetailComponent,
        SaleDialogComponent,
        SaleDeleteDialogComponent,
        SalePopupComponent,
        SaleDeletePopupComponent,
    ],
    entryComponents: [
        SaleComponent,
        SaleListComponent,
        SaleLabelComponent,
        SaleDialogComponent,
        SalePopupComponent,
        SaleDeleteDialogComponent,
        SaleDeletePopupComponent,
    ],
    providers: [
        SaleService,
        SalePopupService,
        SaleResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        SaleLabelComponent
    ]
})
export class EauctionSaleModule {}
