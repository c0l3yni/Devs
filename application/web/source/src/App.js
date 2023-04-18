import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Cart from "./views/Cart/Cart";
import Error from "./views/Error/Error";
import Landing from "./views/Landing/Landing";
import Payment from "./views/Payment/Payment";
import Purchase from "./views/Purchase/purchase";
import SimulateError from "./views/SimulateError/SimulateError";
import HighOrderBoundary from "./ErrorBoundary";
import FinancialDashboard from "./views/FinancialDashboard/FinancialDashboard";
import Products from "./views/Products/Products";
import AdminDashboard from "./views/AdminDashboard/AdminDashboard";
import "../src/css/main.css";



function App() {
	return (
		<div className="">
			<Routes>
				<Route path="*" element={<Navigate to="/error" replace />} />
				<Route path="/error" element={<Error />} />
				<Route
					path="/error/unhandled-exception-simulation"
					element={<SimulateError />}
				/>
				<Route path="/" element={<Landing />} />
				<Route path="/cart" element={<Cart />} />
				<Route path="/payment" element={<Payment />} />
				<Route path="/purchase/:result" element={<Purchase />} />
				<Route path="/financial-dashboard" element={<FinancialDashboard />} />
				<Route path="/products" element={<Products />} />
				<Route path="/admin-dashboard" element={<AdminDashboard />} />
			</Routes>
		</div>
	);
}

export default HighOrderBoundary(App);
