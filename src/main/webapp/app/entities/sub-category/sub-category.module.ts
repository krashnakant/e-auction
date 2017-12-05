import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';

import {
    SubCategoryService,
    SubCategoryPopupService,
    SubCategoryComponent,
    SubCategoryDetailComponent,
    SubCategoryDialogComponent,
    SubCategoryPopupComponent,
    SubCategoryDeletePopupComponent,
    SubCategoryDeleteDialogComponent,
    subCategoryRoute,
    subCategoryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...subCategoryRoute,
    ...subCategoryPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SubCategoryComponent,
        SubCategoryDetailComponent,
        SubCategoryDialogComponent,
        SubCategoryDeleteDialogComponent,
        SubCategoryPopupComponent,
        SubCategoryDeletePopupComponent,
    ],
    entryComponents: [
        SubCategoryComponent,
        SubCategoryDialogComponent,
        SubCategoryPopupComponent,
        SubCategoryDeleteDialogComponent,
        SubCategoryDeletePopupComponent,
    ],
    providers: [
        SubCategoryService,
        SubCategoryPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionSubCategoryModule {}
