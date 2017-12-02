import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ItemAttributeComponent } from './item-attribute.component';
import { ItemAttributeDetailComponent } from './item-attribute-detail.component';
import { ItemAttributePopupComponent } from './item-attribute-dialog.component';
import { ItemAttributeDeletePopupComponent } from './item-attribute-delete-dialog.component';

export const itemAttributeRoute: Routes = [
    {
        path: 'item-attribute',
        component: ItemAttributeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.itemAttribute.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'item-attribute/:id',
        component: ItemAttributeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.itemAttribute.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemAttributePopupRoute: Routes = [
    {
        path: 'item-attribute-new',
        component: ItemAttributePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.itemAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-attribute/:id/edit',
        component: ItemAttributePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.itemAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-attribute/:id/delete',
        component: ItemAttributeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.itemAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
