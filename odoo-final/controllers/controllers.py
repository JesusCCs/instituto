# -*- coding: utf-8 -*-

# from odoo import http
#
#
# class SpaceTravel(http.Controller):
#     @http.route('/space_travel/space_travel/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"
#
#     @http.route('/space_travel/space_travel/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('space_travel.listing', {
#             'root': '/space_travel/space_travel',
#             'objects': http.request.env['space_travel.nave'].search([]),
#         })
#
#     @http.route('/space_travel/space_travel/objects/<model("space_travel.space_travel"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('space_travel.object', {
#             'object': obj
#         })
