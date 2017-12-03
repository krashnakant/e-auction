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
    ItemAttributeListComponent,
    itemAttributeRoute,
    itemAttributePopupRoute,
} from './';
import { EntitySharedModule } from '../entity-shared.module';

const ENTITY_STATES = [
    ...itemAttributeRoute,
    ...itemAttributePopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EntitySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemAttributeComponent,
        ItemAttributeDetailComponent,
        ItemAttributeDialogComponent,
        ItemAttributeDeleteDialogComponent,
        ItemAttributePopupComponent,
        ItemAttributeDeletePopupComponent,
        ItemAttributeListComponent
    ],
    entryComponents: [
        ItemAttributeComponent,
        ItemAttributeDialogComponent,
        ItemAttributePopupComponent,
        ItemAttributeDeleteDialogComponent,
        ItemAttributeDeletePopupComponent,
        ItemAttributeListComponent
    ],
    providers: [
        ItemAttributeService,
        ItemAttributePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        ItemAttributeListComponent
    ]
})
export class EauctionItemAttributeModule {}
