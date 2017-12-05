import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionAdminModule } from '../../admin/admin.module';
import { EauctionItemAttributeModule } from '../item-attribute/item-attribute.module';

import {
    ItemService,
    ItemPopupService,
    ItemComponent,
    ItemDetailComponent,
    ItemDialogComponent,
    ItemPopupComponent,
    ItemDeletePopupComponent,
    ItemDeleteDialogComponent,
    ItemCardComponent,
    itemRoute,
    itemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemRoute,
    ...itemPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionAdminModule,
        EauctionItemAttributeModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemComponent,
        ItemDetailComponent,
        ItemDialogComponent,
        ItemDeleteDialogComponent,
        ItemPopupComponent,
        ItemDeletePopupComponent,
        ItemCardComponent
    ],
    entryComponents: [
        ItemComponent,
        ItemDialogComponent,
        ItemPopupComponent,
        ItemDeleteDialogComponent,
        ItemDeletePopupComponent,
    ],
    providers: [
        ItemService,
        ItemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        ItemCardComponent
    ]
})
export class EauctionItemModule {}
