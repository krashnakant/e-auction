import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import {
    ItemAttributeService,
    ItemAttributePopupService,
    ItemAttributeComponent,
    ItemAttributeDetailComponent,
    ItemAttributeDialogComponent,
    ItemAttributePopupComponent,
    ItemAttributeDeletePopupComponent,
    ItemAttributeDeleteDialogComponent,
    itemAttributeRoute,
    itemAttributePopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemAttributeRoute,
    ...itemAttributePopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemAttributeComponent,
        ItemAttributeDetailComponent,
        ItemAttributeDialogComponent,
        ItemAttributeDeleteDialogComponent,
        ItemAttributePopupComponent,
        ItemAttributeDeletePopupComponent,
    ],
    entryComponents: [
        ItemAttributeComponent,
        ItemAttributeDialogComponent,
        ItemAttributePopupComponent,
        ItemAttributeDeleteDialogComponent,
        ItemAttributeDeletePopupComponent,
    ],
    providers: [
        ItemAttributeService,
        ItemAttributePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionItemAttributeModule {}
