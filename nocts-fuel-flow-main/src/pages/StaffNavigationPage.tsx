import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { LogOut, Fuel, BarChart3 } from "lucide-react";
import { authService } from "@/services/authService";

const StaffNavigationPage = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    authService.logout();
    navigate("/");
  };

  const handleFuelActivation = () => {
    navigate("/dashboard"); // same as your current staff fuel page
  };

  const handleMonthlyReport = () => {
    navigate("/monthly-report");
  };

  return (
    <div className="min-h-screen bg-background">
      <header className="bg-surface border-b border-border px-6 py-4">
        <div className="flex items-center justify-between">
          <h1 className="text-2xl font-bold text-foreground">Staff Dashboard</h1>
          <Button variant="destructive" size="lg" onClick={handleLogout}>
            <LogOut className="mr-2 h-5 w-5" />
            Log Out
          </Button>
        </div>
      </header>

      <main className="flex-1 flex items-center justify-center p-6">
        <div className="w-full max-w-2xl space-y-6">
          <Card onClick={handleFuelActivation} className="cursor-pointer hover:shadow-lg transition">
            <CardContent className="p-8 flex items-center space-x-6">
              <Fuel className="w-10 h-10 text-primary" />
              <div>
                <h3 className="text-xl font-semibold">Activate Fuel Pump</h3>
              </div>
            </CardContent>
          </Card>

          <Card onClick={handleMonthlyReport} className="cursor-pointer hover:shadow-lg transition">
            <CardContent className="p-8 flex items-center space-x-6">
              <BarChart3 className="w-10 h-10 text-info" />
              <div>
                <h3 className="text-xl font-semibold">View Monthly Report</h3>
              </div>
            </CardContent>
          </Card>
        </div>
      </main>
    </div>
  );
};

export default StaffNavigationPage;
