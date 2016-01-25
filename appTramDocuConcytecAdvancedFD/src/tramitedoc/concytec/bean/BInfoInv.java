package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

	public class BInfoInv extends Bean{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int id_inv=0;
		private String nombreinv=null;
		private String apepat=null;
		private String apemat=null;
		private String email=null; 
		private double porcentaje=0;
		
		public void setNombreinv(String nombreinv) {
			this.nombreinv = nombreinv;
		}
		public String getNombreinv() {
			return nombreinv;
		}
		
		public void setId_inv(int id_inv) {
			this.id_inv = id_inv;
		}
		public int getId_inv() {
			return id_inv;
		}
		public void setApepat(String apepat) {
			this.apepat = apepat;
		}
		public String getApepat() {
			return apepat;
		}
		public void setApemat(String apemat) {
			this.apemat = apemat;
		}
		public String getApemat() {
			return apemat;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEmail() {
			return email;
		}
		public void setPorcentaje(double porcentaje) {
			this.porcentaje = porcentaje;
		}
		public double getPorcentaje() {
			return porcentaje;
		}

	}